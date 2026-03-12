package com.ud.ai.mcp.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ud.ai.mcp.pojo.Conference;
import com.ud.ai.mcp.pojo.Session;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class SessionTools {

    private static final Logger logger = LoggerFactory.getLogger(SessionTools.class);
    private final ObjectMapper objectMapper;
    private List<Session> sessions = new ArrayList<>();

    public SessionTools(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        logger.info("Loading sessions from Spring I/O 2025");
        try(InputStream inputStream = SessionTools.class.getResourceAsStream("/sessions.json")) {
            var conference = objectMapper.readValue(inputStream, Conference.class);
            this.sessions = conference.sessions();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sessions from Json file.", e);
        }
    }

    @Tool(name = "spring-io-sessions", description = "Load sessions from Spring I/O 2025")
    public List<Session> finalAllSessions() {
        return sessions;
    }
}
