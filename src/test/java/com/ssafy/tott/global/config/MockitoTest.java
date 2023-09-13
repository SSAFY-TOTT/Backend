package com.ssafy.tott.global.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"test"})
@ExtendWith(MockitoExtension.class)
public abstract class MockitoTest {
}
