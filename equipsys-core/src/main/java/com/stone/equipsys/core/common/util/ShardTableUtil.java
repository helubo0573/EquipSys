package com.stone.equipsys.core.common.util;

import java.util.ArrayList;
import java.util.List;

public class ShardTableUtil
{

	public static List<String> tables = new ArrayList<String>();
	static
	{
		tables.add("cl_user_contacts");
		tables.add("cl_operator_voices");
		tables.add("cl_user_messages");
		tables.add("cl_operator_voices_contact");
		tables.add("cl_operator_rep_application_check");
		tables.add("cl_operator_rep_behavior_check");
		tables.add("cl_operator_rep_contact_region");
		tables.add("cl_operator_rep_trip_info");
		tables.add("cl_operator_rep_main_service");
		tables.add("cl_operator_cell_behavior");
	}

	public static String generateTableNameById(String tableName, long id, long shardId)
	{
		if (tables.contains(tableName))
		{
			long num = id / shardId + 1;
			return tableName + "_" + num;
		} else
		{
			return tableName;
		}
	}

}
