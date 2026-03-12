package com.ud.ai.mcp.pojo;

public record Session(
        String day,
        String time,
        String title,
        String type,
        String[] speakers,
        String room
) {
}
