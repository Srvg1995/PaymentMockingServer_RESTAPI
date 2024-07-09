package mock;


import org.apache.commons.lang3.StringUtils;

import com.jayway.jsonpath.JsonPath;

import spark.Spark;

//SPARK-MOCKING SOLUTION
public class CreditCardMock_$ {

	public static void main(String[] args) {

		Spark.port(8889);//Once the Mocking Solution is developed ,we want to RUN the Mocking Solution using this PORT No.
		Spark.post("/credit-card", (req,res)->{   //This (->{};) is a SYNTAX for develop a MOCKING SOLUTION
			String response = "";
			String card = JsonPath.read(req.body().toString(), "$.creditcard"); //In Gson we have to use $ before using any Json path)
			//if(card.equals("1234567891234")) 
			if(StringUtils.equalsAny(card, "1234567891234","1234567891235"))//In order to compare many values simultaneously,we will use "equalsAny()" present in "StringUtils" class,instead of continuing with &&(AND) symbol for the same 'equals()'.
			{
				response="{\"status\":\"Payment Success\"}";
				res.status(200);
			} 
			else 
			{
				response="{\"status\":\"Payment Failed\"}";
				res.status(400);
			}
			res.type("application/json");
			return response;			
		});

		System.out.println("================Running....................==============");

	}

}
