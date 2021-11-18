package org.iesfm.concurrency;

import java.util.Objects;

public class ClientAccumulator {

    private int clients;

    public ClientAccumulator(int clients) {
        this.clients = clients;
    }

    public synchronized void decrementar() {
        clients--;
    }

    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAccumulator clientAccumulator1 = (ClientAccumulator) o;
        return clients == clientAccumulator1.clients;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clients);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "clients=" + clients +
                '}';
    }
}
