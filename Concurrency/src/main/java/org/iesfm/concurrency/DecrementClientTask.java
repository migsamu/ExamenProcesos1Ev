package org.iesfm.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecrementClientTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(DecrementClientTask.class);

    private ClientAccumulator clientAccumulator;


    public DecrementClientTask(ClientAccumulator clientAccumulator) {
        this.clientAccumulator = clientAccumulator;
    }

    @Override
    public void run() {
        while (clientAccumulator.getClients() > 0) {
            clientAccumulator.decrementar();
            log.info("Cliente servido");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
