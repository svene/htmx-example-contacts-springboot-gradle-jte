package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import org.testcontainers.containers.ContainerLaunchException;

import java.io.IOException;
import java.net.ServerSocket;

public class WaitForPortRelease {
	private final int port;
	private final int maxRetries;
	private final int retryIntervalMillis;

	public WaitForPortRelease(int port, int maxRetries, int retryIntervalMillis) {
		this.port = port;
		this.maxRetries = maxRetries;
		this.retryIntervalMillis = retryIntervalMillis;
	}

	protected void waitUntilReady() {
		for (int retryCount = 0; retryCount < maxRetries; retryCount++) {
			if (!isPortOccupied()) {
				System.out.println("port available");
				return; // Port is not occupied anymore
			}
			System.out.printf("port occupied -> waiting unit available (%d/%d)%n", retryCount + 1, maxRetries);

			try {
				Thread.sleep(retryIntervalMillis);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		throw new ContainerLaunchException("Port is still occupied after waiting.");
	}

	private boolean isPortOccupied() {
		try (ServerSocket socket = new ServerSocket(port)) {
			// If the socket is successfully created, the port is occupied.
			socket.close();
			return false;
		} catch (IOException e) {
			// If an IOException is thrown, the port is not occupied.
			return true;
		}
	}

}
