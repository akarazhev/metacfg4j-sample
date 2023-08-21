package com.github.akarazhev.metaconfig.sample;

import com.github.akarazhev.metaconfig.Constants;
import com.github.akarazhev.metaconfig.api.Config;
import com.github.akarazhev.metaconfig.api.MetaConfig;
import com.github.akarazhev.metaconfig.api.Property;
import com.github.akarazhev.metaconfig.engine.web.server.Server;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.github.akarazhev.metaconfig.Constants.Settings.DB_DIALECT;
import static com.github.akarazhev.metaconfig.Constants.Settings.FETCH_SIZE;
import static com.github.akarazhev.metaconfig.Constants.Settings.POSTGRE;

public class Main {

    public static void main(String[] args) {
        final MetaConfig metaConfig = metaConfig();
        System.out.println("Number of rows in the database: " + metaConfig.getNames().count());
        System.out.println("To terminate the program press: q/Q ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            if ("q".equalsIgnoreCase(scanner.next())) {
                System.out.print("Termination of the program...");
                metaConfig.close();
                return;
            } else {
                System.out.print("To terminate the program press: q/Q: ");
            }
        }
    }

    private static DataSource getDataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/pmon");
        dataSource.setMaximumPoolSize(Runtime.getRuntime().availableProcessors() * 2 + 1);
        dataSource.addDataSourceProperty("user", "sa");
        dataSource.addDataSourceProperty("password", "sa");
        return dataSource;
    }

    public static MetaConfig metaConfig() {
        // Set a fetch size
        final Map<String, Object> settings = new HashMap<>();
        settings.put(FETCH_SIZE, 100);
        settings.put(DB_DIALECT, POSTGRE);
        // Create the web server config
        final Config webServer = new Config.Builder(Server.Settings.CONFIG_NAME,
                Arrays.asList(
                        new Property.Builder(Server.Settings.HOSTNAME, "localhost").build(),
                        new Property.Builder(Constants.Endpoints.ACCEPT_CONFIG, "accept_config").build(),
                        new Property.Builder(Constants.Endpoints.CONFIG_NAMES, "config_names").build(),
                        new Property.Builder(Constants.Endpoints.CONFIG, "config").build(),
                        new Property.Builder(Server.Settings.PORT, 8000).build(),
                        new Property.Builder(Server.Settings.BACKLOG, 0).build(),
                        new Property.Builder(Server.Settings.KEY_STORE_FILE, "./data/metacfg4j.keystore").build(),
                        new Property.Builder(Server.Settings.ALIAS, "alias").build(),
                        new Property.Builder(Server.Settings.STORE_PASSWORD, "password").build(),
                        new Property.Builder(Server.Settings.KEY_PASSWORD, "password").build())).build();
        // Create the meta configuration
        return new MetaConfig.Builder().webServer(webServer).dataSource(getDataSource()).dbSettings(settings).build();
    }
}