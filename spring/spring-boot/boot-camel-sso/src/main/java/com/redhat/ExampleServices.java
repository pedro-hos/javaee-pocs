package com.redhat;

import java.util.List;

public class ExampleServices {
	
	 public static void example(List<MyBean> bodyIn) {
		 	
		 	bodyIn.forEach(b -> {
		 		b.setId(b.getId() * 10);
		 		b.setName("[private] Hello " + b.getName());
		 	});
		 	
	    }
	

}
