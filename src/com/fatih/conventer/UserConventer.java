	package com.fatih.conventer;
	
	import org.bson.types.ObjectId;
	
	import com.fatih.model.User;
	import com.mongodb.BasicDBObjectBuilder;
	import com.mongodb.DBObject;
	
	public class UserConventer {
	
		public static DBObject toDBObject(User user) {
			
			BasicDBObjectBuilder builder=BasicDBObjectBuilder.start()
					.append("name", user.getName())
					.append("email", user.getEmail())
					.append("password", user.getPassword());
			
			if(user.getId()!=null){
				builder=builder.append("_id", new ObjectId(user.getId()));
			}
			
			
			
			return builder.get();
		}

		public static User toUser(DBObject dbObject) {
			
			User u=new User();
			
			u.setName((String)dbObject.get("name"));
			u.setEmail((String)dbObject.get("email"));
			u.setPassword((String)dbObject.get("password"));
			
			ObjectId id=(ObjectId) dbObject.get("_id");
			
			u.setId(id.toString());
			
			return u;
			
			
		}
	
	}
