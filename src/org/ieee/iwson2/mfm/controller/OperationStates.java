package org.ieee.iwson2.mfm.controller;

public interface OperationStates {
	enum Operation_States {
		SITES /* Define Sites */, 
		CELLS /*Define Cells*/, 
		ECR /* Define Expected Cell Ranges */, 
		PCI /*Define PCI Forbidden*/, 
		PRACH /*Define PRACH Forbidden*/, 
		REPEL /*Show Repel Factor*/,
		CLEAR /*Clear the network*/
	};
}
