package com.blackhorse.auction.configuration;

import com.blackhorse.auction.AuctionBidderApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;

@ExtendWith({MariaDb4jExtension.class, SpringExtension.class})
@ActiveProfiles("test")
@SpringBootTest(classes = AuctionBidderApplication.class)
@Slf4j
@Rollback
@AutoConfigureMockMvc
public class TestBase {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SpringApplicationContext springApplicationContext;

    @Autowired
    private TruncateDatabaseService truncateDatabaseService;

    private void prepareGlobalConfiguration() {
    }

    @BeforeEach
    protected void setUp() {
        this.prepareGlobalConfiguration();
        try {
            truncateDatabaseService.restartIdWith(1, true, null);
        } catch (SQLException e) {
            log.info(e.getStackTrace().toString());
        } finally {
            truncateDatabaseService.closeResource();
        }
        springApplicationContext.setApplicationContext(context);
    }
}
