package com.book.store.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class TMultiplexedClient {
	public static void main(String[] args) {

		try {
			TTransport transport;
			transport = new TSocket("localhost", 9090);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			TMultiplexedProtocol add = new TMultiplexedProtocol(protocol, "ADDITION");
			AdditionService.Client addClient = new AdditionService.Client(add);
			TMultiplexedProtocol multiply = new TMultiplexedProtocol(protocol, "MULTIPLICATION");
			MultiplicationService.Client multiplyClient = new MultiplicationService.Client(multiply);
			System.out.println(multiplyClient.multiply(4, 2));
			System.out.println(addClient.add(2, 2));
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}
}
