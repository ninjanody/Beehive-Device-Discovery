/*
 * Copyright 2013-2015, Juha Lindfors. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.openremote.beehive.discovery.service;

import org.openremote.beehive.discovery.model.rest.DeviceDiscoveryReader;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;


/**
 * A JAX-RS 2.0 application of Beehive Device Discovery Service. <p>
 *
 * This JAX-RS application aggregates the relevant REST resources, providers and
 * features that compose the device discovery service. <p>
 *
 * This implementation uses the explicit resource registration via {@link #getClasses()} method
 * to support pre-Servlet 3.0 containers. It currently assumes a servlet container-based
 * deployment.
 *
 * @author Juha Lindfors
 */
public class DeviceDiscoveryService extends Application
{

  // Class Members --------------------------------------------------------------------------------

  private static final Set<Class<?>> resourceClasses = new HashSet<Class<?>>(10);

  static
  {
    resourceClasses.add(AddDevice.class);
  }

  private static final Set<Class<?>> providerClasses = new HashSet<Class<?>>();

  static
  {
    providerClasses.add(DeviceDiscoveryReader.class);
    //providerClasses.add(UserAuthorization.class);
  }


  @Context private ServletContext webapp;


  // Constructors ---------------------------------------------------------------------------------

  public DeviceDiscoveryService()
  {
    //System.setProperty("jersey.config.server.tracing.type", "ALL");
  }



  // Application Overrides ------------------------------------------------------------------------

  @Override public Set<Class<?>> getClasses()
  {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.addAll(resourceClasses);
    classes.addAll(providerClasses);

    return classes;
  }


}

