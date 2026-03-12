package com.ud.ai.mcp.pojo;

import java.util.List;

public record Conference(
        String name,
        Integer year,
        String[] dates,
        String location,
        List<Session> sessions
) {
}
