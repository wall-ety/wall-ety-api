package com.hei.wallet.wallety.controller;

import com.hei.wallet.wallety.endpoint.rest.controller.HealthController;
import com.hei.wallet.wallety.model.Dummy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class HealthControllerIT {
    @Autowired
    private HealthController healthController;

    @Test
    void getDummiesShouldBeSuccess() throws SQLException {
        final int DUMMIES_TEST = 3;
        List<Dummy> dummies = healthController.getDummies();
        Assertions.assertFalse(dummies.isEmpty());
        Assertions.assertEquals(dummies.size(), DUMMIES_TEST);
    }

    @Test
    void pingPong(){
        Assertions.assertEquals(healthController.ping(), "pong");
    }
}