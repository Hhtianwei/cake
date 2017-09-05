package com.tim.cake.online.security.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;


public class DefaultBruteForceAttackCounter
{
	private Integer maxFailedLogins;
	private Integer cacheExpiration;
	private Integer cacheSizeLimit;
	private Map<String, LoginFailure> failureMap;

	public DefaultBruteForceAttackCounter(int maxFailedLogins, int cacheExpiration, int cacheSizeLimit)
	{
		this.maxFailedLogins = maxFailedLogins;//最大登录失败次数
		this.cacheExpiration = cacheExpiration;//登录锁定时间(如果登录超过了指定的次数，对账号进行限制登录，限制的时间)
		this.cacheSizeLimit = cacheSizeLimit;//记录登录用户错误的最大个数
		failureMap = new ConcurrentHashMap<String, LoginFailure>((int) 0.5 * cacheSizeLimit);
	}

	public void addFailureUser(String userId)
	{
		LoginFailure userInfo = get(userId);
		Date firstTime = userInfo.getFirstLoginTime();
		Date expireDate = DateUtils.addMinutes(firstTime, this.cacheExpiration);
		if (new Date().after(expireDate))
		{
			userInfo.setCount(1);
			userInfo.setFirstLoginTime(new Date());
		}
		else
		{
			userInfo.setCount(userInfo.getCount() + 1);
		}
	}

	public boolean isAttrack(String userId)
	{
		LoginFailure userInfo = get(userId);
		if (this.maxFailedLogins >= userInfo.getCount())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	protected LoginFailure get(String userId)
	{
		LoginFailure result = failureMap.get(userId);

		if (result == null)
		{
			result = new LoginFailure();
			failureMap.put(userId, result);
			if (failureMap.size() > cacheSizeLimit)
			{
				evict();
			}
		}
		return result;
	}

	/**
	 * 移除过期的数据
	 */
	protected void evict()
	{
		if (failureMap.size() > cacheSizeLimit)
		{
			final Iterator<String> cacheIterator = failureMap.keySet().iterator();
			final Date dateLimit = DateUtils.addMinutes(new Date(), -cacheExpiration);
			while (cacheIterator.hasNext())
			{
				final String userKey = cacheIterator.next();
				final LoginFailure value = failureMap.get(userKey);
				if (value.getFirstLoginTime().before(dateLimit))
				{
					failureMap.remove(userKey);
				}
			}
		}
	}

	public void removeLoginFailureUser(String userName)
	{
		if (StringUtils.isNotEmpty(userName))
		{
			if (failureMap.containsKey(userName))
			{
				failureMap.remove(userName);
			}

		}
	}

	class LoginFailure
	{
		private int count;
		private Date firstLoginTime;

		LoginFailure()
		{
			count = 0;
			firstLoginTime = new Date();
		}

		public int getCount()
		{
			return count;
		}

		public void setCount(int count)
		{
			this.count = count;
		}

		public Date getFirstLoginTime()
		{
			return firstLoginTime;
		}

		public void setFirstLoginTime(Date firstLoginTime)
		{
			this.firstLoginTime = firstLoginTime;
		}
	}
}
