com.xiaoy.base.service.CommonService<T>
Service层公用接口，接口中的方法与公用Dao中的相同，目的就是为了避免重复代码。所有Service层可以根据需要选择继承。

-------------------------------------------------------------------------------------

com.xiaoy.base.service.impl.CommonServiceImpl<T>
Service层实现公用接口类，所有Service层可以根据需要选择继承。如果继承则需要注意以下几点：

1.在com.xiaoy.base.service.impl.CommonServiceImpl<T>是一个抽象类，其中有一个抽象方法，用于CommonDao的注入。
	public abstract void setCommonDao(CommonDao<T> commonDao);
	
2.在子类中必须实现些方法，为其提供CommonDao，否则出现空指针异常
如：
	private MenuDao menuDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Menu> commonDao)
	{
		this.menuDao = (MenuDao) commonDao;
		super.commonDao = commonDao;
	}

----------------------------------------------------------------------------------------------------------






















