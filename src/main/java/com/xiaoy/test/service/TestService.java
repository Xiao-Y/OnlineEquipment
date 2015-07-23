package com.xiaoy.test.service;

import java.util.List;

import com.xiaoy.base.entities.Test;

public interface TestService
{

	public List<Test> getAllNewFile();

	public void saveTest(Test t);

	public void updateTest(Test t);

	public void deleteTest(Test t);

}
