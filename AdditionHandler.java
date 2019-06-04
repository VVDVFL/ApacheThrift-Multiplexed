package com.book.store.service;

import org.apache.thrift.TException;

public class AdditionHandler implements AdditionService.Iface{

	@Override
	public int add(int n1, int n2) throws TException {
		// TODO Auto-generated method stub
		return n1 + n2;
	}

}
