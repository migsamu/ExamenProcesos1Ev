package org.iesfm.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger log = LoggerFactory.getLogger(ClientAccumulator.class);

    public static void main(String[] args) {

        log.info("Introduce el numero de clientes que tiene la cola");
        int clients = scanner.nextInt();
        scanner.nextLine();
        ClientAccumulator clientAccumulator = new ClientAccumulator(clients);

        Thread caja1 = new Thread(new DecrementClientTask(clientAccumulator));
        Thread caja2 = new Thread(new DecrementClientTask(clientAccumulator));

        caja1.start();
        caja2.start();

        try {
            caja1.join();
            caja2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Cola vacía");

    }
}
