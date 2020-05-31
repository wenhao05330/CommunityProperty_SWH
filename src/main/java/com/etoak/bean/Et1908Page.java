package com.etoak.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Et1908Page<T> {

	private int pageNum;// 页码

	private int pageSize; // 每页显示记录数

	private List<T> rows; // 数据

	private long total; // 数据总量

	private int pageCount; // 总页数

}
