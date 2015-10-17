package com.mobiletourismapp.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class States {

	private Integer code;
	private List<Result> result = new ArrayList<Result>();

	/**
	 * 
	 * @return The code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 *            The code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * 
	 * @return The result
	 */
	public List<Result> getResult() {
		return result;
	}

	/**
	 * 
	 * @param result
	 *            The result
	 */
	public void setResult(List<Result> result) {
		this.result = result;
	}

	public class Result {

		private String code;
		private String name;
		private Object states;

		/**
		 * 
		 * @return The code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * 
		 * @param code
		 *            The code
		 */
		public void setCode(String code) {
			this.code = code;
		}

		/**
		 * 
		 * @return The name
		 */
		public String getName() {
			return name;
		}

		/**
		 * 
		 * @param name
		 *            The name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 
		 * @return The states
		 */
		public Object getStates() {
			return states;
		}

		/**
		 * 
		 * @param states
		 *            The states
		 */
		public void setStates(Object states) {
			this.states = states;
		}

	}
}