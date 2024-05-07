package de.fhws.fiw.fds.suttondemo.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.suttondemo.server.LocationDao;
import de.fhws.fiw.fds.suttondemo.server.api.models.Location;

public class LocationStorage extends AbstractInMemoryStorage<Location> implements LocationDao {
}
