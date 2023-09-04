package com.techchallenge.infrastructure.api.request;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public record OrderResponse(Long id, CustomerResponse custumer, List<ProductResponse> products, 
		
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)	
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime dateOrderInit,
		
		@JsonDeserialize(using = LocalDateTimeDeserializer.class) 		
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime dateOrderFinish,
		Long minutesDurationOrder,
		String statuOrder) {

}
