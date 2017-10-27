package my.utm.lims.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import my.utm.lims.model.Laboratory;

@Component
public class LaboratoryRepository {
	public static Map<Long, Laboratory> storage;
	private static Long lastId = 0L;

	public LaboratoryRepository() {
		storage = new HashMap<Long, Laboratory>();
	}

	public Laboratory findOne(Long id) {
		return storage.get(id);
	}

	public List<Laboratory> findAll() {
		return new ArrayList<Laboratory>(storage.values());
	}

	public void create(Laboratory lab) {
		lab.setId(++lastId);
		storage.put(lastId, lab);
	}
	
	public void delete(Long id) {
		storage.remove(id);
	}

}
