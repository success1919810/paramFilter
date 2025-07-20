package org.example.paramfilter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqInfo {
    private String url;
    private String method;
    private String ip;
    private String userAgent;
    private String traceId;
    private String body;
}
