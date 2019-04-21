package model;

import trees.AVLTree;

import java.util.Date;
import java.util.List;

public class Action implements Ibehavior{

    private String name;
    private AVLTree<Date,Historical> historicalAVLTree;

    public Action(String action){

    }

	@Override
	public double getAmountMax(java.sql.Date dateStart, java.sql.Date dateEnd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAmountMin(java.sql.Date dateStart, java.sql.Date dateEnd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public java.sql.Date getTimeMaxIncrease() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.Date getTimeMinIncrease() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Double> getDatasToShowStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
