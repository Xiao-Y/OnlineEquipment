package com.xiaoy.util;

public class JsonResult
{
	private boolean success = false;
	private String message;
	private Long total;
	private Object root;

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Long getTotal()
	{
		return total;
	}

	public void setTotal(Long total)
	{
		this.total = total;
	}

	public Object getRoot()
	{
		return root;
	}

	public void setRoot(Object root)
	{
		this.root = root;
	}
}
