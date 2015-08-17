package com.palm.lingcai.entity.entityenum;

/**
 * <p>标题：二维码尺寸枚举 </p>
 * <p>公司: 北京阳光彩通 </p>
 * <p>创建日期：2014年8月6日 上午9:45:58</p>
 * <p>类全名：com.palm.lingcai.entity.entityenum.QRcodeSizeEnum</p>
 * <p>作者：JIJI </p>
 * <p>版本：1.0 </p>
 */
public enum QRcodeSizeEnum
{
	SIZE_8CM("8cm", "0.5m", 300), SIZE_12CM("12cm", "0.8m", 400), SIZE_15CM("15cm", "1m", 500), SIZE_30CM("30cm", "1.5cm", 600), SIZE_50CM("50cm", "2.5cm", 700);
	private String	sideLength;
	private String	scanDistance;
	private int		size;

	private QRcodeSizeEnum(String sideLength, String scanDistance, int size)
	{
		this.sideLength = sideLength;
		this.scanDistance = scanDistance;
		this.size = size;
	}

	// 二维码边长(cm)
	public String getSideLength()
	{
		return sideLength;
	}

	// 扫描距离(m)
	public String getScanDistance()
	{
		return scanDistance;
	}

	// 二维码尺寸
	public int getSize()
	{
		return size;
	}

	static public String toJson()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < QRcodeSizeEnum.values().length; i++)
		{
			if (i > 0)
			{
				sb.append(",");
			}
			QRcodeSizeEnum size = (QRcodeSizeEnum.values())[i];
			sb.append("{\"ord\":\"");
			sb.append(size.ordinal());
			sb.append("\",\"sideLength\":\"");
			sb.append(size.sideLength);
			sb.append("\",\"scanDistance\":\"");
			sb.append(size.scanDistance);
			sb.append("\",\"size\":");
			sb.append(new Integer(size.size).toString());
			sb.append("\"}");
		}
		return sb.toString();
	}
}
