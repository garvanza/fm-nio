package garvanza.fm.nio.db;

import com.mongodb.DBCursor;

public class DBQueryI implements DBQuery<DBCursor> {

	private DBQueryI() {
		
	}

	@Override
	public DBCursor search(String where, String what) {

		return null;
	}

}
