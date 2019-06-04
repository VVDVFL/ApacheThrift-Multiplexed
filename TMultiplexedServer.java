package com.book.store.service;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class TMultiplexedServer {


  public static MultiplicationService.Processor processor;
  
  public static TMultiplexedProcessor multipleProcessor = new TMultiplexedProcessor();

  public static void main(String [] args) {
	MultiplicationHandler multiplicationHandler;
	AdditionHandler additionHandler;
    try {
    	multiplicationHandler = new MultiplicationHandler();
    	additionHandler = new AdditionHandler();
    	multipleProcessor.registerProcessor("ADDITION", new AdditionService.Processor(new AdditionHandler()));
    	multipleProcessor.registerProcessor("MULTIPLICATION", new MultiplicationService.Processor(new MultiplicationHandler()));

      Runnable simple = new Runnable() {
        public void run() {
          simple(multipleProcessor);
        }
      };      
     
      new Thread(simple).start();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

  public static void simple(TMultiplexedProcessor multipleProcessor2) {
    try {
      TServerTransport serverTransport = new TServerSocket(9090);
      TServer server = new TSimpleServer(new Args(serverTransport).processor(multipleProcessor2));

      System.out.println("Starting the simple server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
}

