package garvanza.fm.nio.db;

public interface DBQuery<T> {

	public T search(String where,String what);
}
