package com.coder2client.services;

import com.coder2client.entities.Address;
import com.coder2client.entities.GeoLocation;

public interface GeoLocationService {
    GeoLocation geoLocate(Address address);
}
