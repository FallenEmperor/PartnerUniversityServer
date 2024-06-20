/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.suttondemo.server.database;

import org.eclipse.jdt.internal.compiler.lookup.ModuleScope;

import de.fhws.fiw.fds.suttondemo.server.database.inmemory.ModuleStorage;
import de.fhws.fiw.fds.suttondemo.server.database.inmemory.PartnerModuleStorage;
import de.fhws.fiw.fds.suttondemo.server.database.inmemory.PartnerStorage;

public class DaoFactory {

    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }

        return INSTANCE;
    }

    private final PartnerDao partnerDao;

    private final ModuleDao moduleDao;

    private final PartnerModuleDao partnerModuleDao;

    private DaoFactory() {
        this.partnerDao = new PartnerStorage();
        this.moduleDao = new ModuleStorage();
        this.partnerModuleDao = new PartnerModuleStorage(this.moduleDao);
    }

    public PartnerDao getPartnerDao() {
        return this.partnerDao;
    }

    public PartnerModuleDao getPartnerModuleDao() {
        return partnerModuleDao;
    }

    public ModuleDao getModuleDao() {
        return moduleDao;
    }
}
