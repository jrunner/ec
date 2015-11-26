package com.t2t.top.base.monitor;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 监控类
 */
public class Monitor {

	private static Map<String, MonitorItem> items = new ConcurrentHashMap<String, MonitorItem>();
	private static Map<String, MonitorAtomicLong> values = new ConcurrentHashMap<String, MonitorAtomicLong>();
	private static Map<String, MonitorItem> jvmItems = new ConcurrentHashMap<String, MonitorItem>();
	
	private static Map<String, Long> currentItems = new HashMap<String, Long>();
	private static Map<String, String> currentSettingItems = new HashMap<String, String>();
	
	
	private static Timer timer;
	
	static {
		timer = new Timer("Monitor", true);
		timer.schedule(new MonitorTask(), 0, 2000);
	}
	
	/**
	 * 记录一条统计，统计数加1，时间计算一分钟内的平均值
	 * @param name
	 * @param time
	 * @return MonitorItem
	 */
	public static MonitorItem recordOne(String name, long time) {
		return recordMany(name, 1, time);
	}
	
	public static MonitorItem recordOne(String name) {
		return recordMany(name, 1, 0);
	}
	
	public static MonitorItem decrRecord(String name) {
		return recordMany(name, -1, 0);
	}
	/**
	 * 可以一次记录多个统计次数和时间
	 * @param name
	 * @param count
	 * @param time
	 * @return MonitorItem
	 */
	public static MonitorItem recordMany(String name, long count, long time) {
		
		MonitorItem item = items.get(name);
		if (item == null) {
			item = new MonitorItem();
			items.put(name, item);
		}
		item.add(count, time);
		return item;
	}
	
	public static MonitorAtomicLong recordSize(String name, long size) {
		MonitorAtomicLong v = values.get(name);
		if (v == null) {
			v = new MonitorAtomicLong();
			values.put(name, v);
		}
		v.set(size);
		return v;
	}
	/**
	 * 记录具体值的统计参数，可以增加或者减少统计值
	 * @param name
	 * @param count
	 * @return MonitorItem
	 */
	public static MonitorAtomicLong recordValue(String name, long count) {
		MonitorAtomicLong v = values.get(name);
		if (v == null) {
			v = new MonitorAtomicLong();
			values.put(name, v);
		}
		v.addAndGet(count);
		return v;
	}
	
	public static Map<String, Long> getValues() {
		return currentItems;
	}
	
	public static Map<String, String> getSettingValues() {
		return currentSettingItems;
	}
	
	
	public static String metaValueRegularization(String metaValue) {
		int end = metaValue.indexOf('\n');
		if (end < 0) {
			end = metaValue.length();
		}
		
		if (end > 1024) {
			end = 1024;
		}
		
		return metaValue.substring(0, end);
	}
	
	
	
	public static class MonitorItem {
		private long count;
		private long time;
		private String countAlert = null;
		private String timeAlert = null;
		private String treePath = null;
		private String timeDescription = null;
		private String countDescription = null;
		
		protected synchronized void add(long count, long time) {
			this.count = this.count + count;
			this.time = this.time + time;
		}
		
		protected synchronized MonitorItem dumpAndClearItem() {
			MonitorItem item = new MonitorItem();
			item.count = this.count;
			item.time = this.time;
			this.count = 0;
			this.time = 0;
			item.countAlert = this.countAlert;
			item.timeAlert = this.timeAlert;
			item.treePath = this.treePath;
			item.timeDescription = this.timeDescription;
			item.countDescription = this.countDescription;
			return item;
		}
		
		/**
		 * 设置计数器默认报警值
		 * @param countAlert
		 * @return MonitorItem
		 */
		public MonitorItem setCountAlert(String countAlert) {
			if (null == this.countAlert) {
				countAlert = metaValueRegularization(countAlert);
				if (! "".equals(countAlert)) {
					this.countAlert = countAlert;
				}
			}
			return this;
		}
		
		/**
		 * 设置监控时间默认报警值
		 * @param timeAlert
		 * @return MonitorItem
		 */
		public MonitorItem setTimeAlert(String timeAlert) {
			if (null == this.timeAlert){
				timeAlert = metaValueRegularization(timeAlert);
				if (! "".equals(timeAlert)) {
					this.timeAlert = timeAlert;
				}
			}
			return this;
		}
		
		/**
		 * 设置监控图挂在路径
		 * @param treePath
		 * @return MonitorItem
		 */
		public MonitorItem setTreePath(String treePath) {
			if (null == this.treePath) {
				treePath = metaValueRegularization(treePath);
				if (! "".equals(treePath)) {
					this.treePath = treePath;
				}
			}
			return this;
		}
		
		/**
		 * 设置监控时间描述
		 * @param description
		 * @return MonitorItem
		 */
		public MonitorItem setTimeDescription(String description) {
			if (null == this.timeDescription) {
				description = metaValueRegularization(description);
				if (! "".equals(description)) {
					this.timeDescription = description;
				}
			}
			return this;
		}
		
		/**
		 * 设置计数器描述
		 * @param description
		 * @return MonitorItem
		 */
		public MonitorItem setCountDescription(String description) {
			if (null == this.countDescription) {
				description = metaValueRegularization(description);
				if (! "".equals(description)) {
					this.countDescription = description;
				}
			}
			return this;
		}
	}
	
	public static class MonitorAtomicLong {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8515600818767598583L;
		private String valueAlert = null;
		private String treePath = null;
		private String description = null;
		
		// java里没有protected extends，为了可见性，将AtomicLong变为成员变量
		private AtomicLong value = new AtomicLong();
		
		protected void set(long newValue) {
			value.set(newValue);
		}
		
		protected long addAndGet(long newValue) {
			return value.addAndGet(newValue);
		}
		
		protected long get() {
			return value.get();
		}

		/**
		 * 设置监控值默认报警值
		 * @param valueAlert
		 * @return MonitorItem
		 */
		public MonitorAtomicLong setValueAlert(String valueAlert) {
			if (null == this.valueAlert) {
				valueAlert = metaValueRegularization(valueAlert);
				if (! "".equals(valueAlert)) {
					this.valueAlert = valueAlert;
				}
			}
			return this;
		}

		/**
		 * 设置监控值挂在路径
		 * @param treePath
		 * @return MonitorItem
		 */
		public MonitorAtomicLong setTreePath(String treePath) {
			if (null == this.treePath) {
				treePath = metaValueRegularization(treePath);
				if (! "".equals(treePath)) {
					this.treePath = treePath;
				}
			}
			return this;
		}

		/**
		 * 设置监控值描述
		 * @param description
		 * @return MonitorItem
		 */
		public MonitorAtomicLong setDesription(String description) {
			if (null == this.description) {
				description = metaValueRegularization(description);
				if (! "".equals(description)) {
					this.description = description;
				}
			}
			return this;
		}
	}
	
	private static long lastUpdate = 0;
	
	private static class MonitorTask extends TimerTask {

		@Override
		public void run() {
			try {
				long current = System.currentTimeMillis();
				if (current - lastUpdate < 50000L) {
					return;
				}
				
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(current);
				if (cal.get(Calendar.SECOND) > 10) {
					return;
				}
				lastUpdate = current;
				
				Map<String, Long> ret = new HashMap<String, Long>();
				
				Map<String, String> settingRet = new HashMap<String, String>();
				
				ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
				
				ret.put("JVM_Thread_Count", (long)threadBean.getThreadCount());
				
				List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
				
				for (GarbageCollectorMXBean bean : beans) {
					String name = "JVM_" + bean.getName();
					long count = bean.getCollectionCount();
					long time = bean.getCollectionTime();
					MonitorItem item = jvmItems.get(name);
					if (item == null) {
						item = new MonitorItem();
						item.add(count, time);
						jvmItems.put(name, item);
					}
					
					
					ret.put(makeName(name + "_Count"), count - item.count);
					if (count - item.count > 0) {
						ret.put(makeName(name + "_Time"), (time - item.time) / (count - item.count));
					}
					
					item = new MonitorItem();
					item.add(count, time);
					jvmItems.put(name, item);
				}
				
				
				for (Entry<String, MonitorItem> entry : items.entrySet()) {
					String name = entry.getKey();
					MonitorItem item = entry.getValue().dumpAndClearItem();
					long count = item.count;
					long time = item.time;
					String countName = makeName(name + "_Count");
					String timeName = makeName(name + "_Time");
					String countAlert = item.countAlert;
					String timeAlert = item.timeAlert;
					String treePath = item.treePath;
					String timeDescription = item.timeDescription;
					String countDescription = item.countDescription;
					
					if (null != countAlert) {
						settingRet.put(countName + "_Alert", countAlert);
					}

					if (null != timeAlert) {
						settingRet.put(timeName + "_Alert", timeAlert);
					}
					
					if (null != treePath) {
						settingRet.put(timeName + "_TreePath", treePath);
					}
					
					
					if (null != timeDescription) {
						settingRet.put(timeName + "_Description", timeDescription);
					}
					
					if (null != countDescription) {
						settingRet.put(countName + "_Description", countDescription);
					}
					
					ret.put(countName, count);
					if (count > 0) {
						ret.put(timeName, time / count);
					} else {
						ret.put(timeName, 0L);
					}
				}
				
				for (Entry<String, MonitorAtomicLong> entry : values.entrySet()) {
					String valueName = entry.getKey() + "_Value";
					String valueAlert = entry.getValue().valueAlert;
					String description = entry.getValue().description;
					String treePath = entry.getValue().treePath;

					if (null != valueAlert) {
						settingRet.put(valueName + "_Alert", valueAlert);
					}
					
					if (null != treePath) {
						settingRet.put(valueName + "_TreePath", treePath);
					}
					
					if (null != description) {
						settingRet.put(valueName + "_Description", description);
					}
					ret.put(makeName(valueName), entry.getValue().get());
				}
				
				currentItems = Collections.unmodifiableMap(ret);
				currentSettingItems = Collections.unmodifiableMap(settingRet);
				
			} catch (Throwable e) {
				// ignore
			}
		}

		private String makeName(String name) {
			return name.replaceAll(" ", "_");
		}
	}
}
