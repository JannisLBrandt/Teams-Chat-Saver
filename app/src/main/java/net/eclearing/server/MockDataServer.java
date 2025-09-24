package net.eclearing.server;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import com.github.tomakehurst.wiremock.WireMockServer;

public class MockDataServer {
    public static void main(String[] args) {
	WireMockServer server = new WireMockServer(options().port(8181).usingFilesUnderDirectory("src/main/resources/wiremock"));
	server.start();
	System.out.println("Server running successfully at: " + server.baseUrl());
    }
}
