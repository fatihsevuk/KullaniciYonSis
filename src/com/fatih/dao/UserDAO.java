package com.fatih.dao;



import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.fatih.conventer.UserConventer;
import com.fatih.model.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class UserDAO {

	//private DBCollection dbCollection;

	private DBCollection dbCollection;
	//List<Document> userList=new ArrayList<>();

	public UserDAO(MongoClient mongoClient) {
		this.dbCollection=mongoClient.getDB("usersDB").getCollection("users");
		 
	}


	public User createUser(User user){
		

		DBObject dbObject=UserConventer.toDBObject(user);
		this.dbCollection.insert(dbObject); 
		ObjectId id=(ObjectId) dbObject.get("_id");
		user.setId(id.toString());
	
		return user;
	}


	public List<User> readAllUser() {
		List<User> data=new ArrayList<User>();
		DBCursor cursor=dbCollection.find();
		while (cursor.hasNext()) {
			DBObject dbObject =(DBObject)cursor.next();
			User u=UserConventer.toUser(dbObject);
			System.out.println("Passwor is= "+u.getPassword());
			data.add(u);
			
			
		}
		
		return data;
		
		
	}
	
	
	public User readUser(User user){
		
		DBObject query=BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(user.getId())).get();
		
		DBObject data= this.dbCollection.findOne(query);
		
		return UserConventer.toUser(data);
	}
	
	
	


	public void updateUser(User user) {
		
		DBObject query=BasicDBObjectBuilder.start()
				.append("_id",new ObjectId(user.getId())).get();
		
		this.dbCollection.update(query, UserConventer.toDBObject(user));
		
		
	}
	
	
	public void deletUser(User user){
		
		DBObject query=BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(user.getId())).get();
		this.dbCollection.remove(query);
		
		
		
	}
	
	
	
	
	
	

}
