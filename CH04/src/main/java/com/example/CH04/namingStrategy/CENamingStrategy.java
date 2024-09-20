package com.example.CH04.namingStrategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CENamingStrategy extends PhysicalNamingStrategyStandardImpl {
    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return new Identifier("CE_"+name.getText(), name.isQuoted());
    }

    //spring.jpa.hibernate.naming.physical-strategy=com.example.CH04.namingStrategy.CENamingStrategy
}
