com.xiaoy.base.dao.CommonDao<T>
Dao层公用接口类，所有的Dao层接口都要继承。此接口中提供了大量的公用方法，可以避免重复代码

---------------------------------------------------------------------------------------------

com.xiaoy.base.dao.impl.CommonDaoImpl<T>
Dao层公用接口实现类，所有的Dao层实现类都要继承。实现了com.xiaoy.base.dao.CommonDao<T>中的所有方法。

----------------------------------------------------------------------------------------------

实现类中还注入sessionFactory,使用泛型实现公用HQL语句

@Resource
private SessionFactory sessionFactory;

public Session getSession()
{
	return this.sessionFactory.getCurrentSession();
}

// 获取当前类的父类的类型(泛型的转换)
@SuppressWarnings("rawtypes")
Class entityClass = GenericSuperclass.getClass(this.getClass());

-----------------------------------------------------------------------------------------------
























