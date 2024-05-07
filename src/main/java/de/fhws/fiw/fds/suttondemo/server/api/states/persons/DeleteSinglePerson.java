/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.fiw.fds.suttondemo.server.api.states.persons;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Person;

public class DeleteSinglePerson<R> extends AbstractDeleteState<R, Person> {

    public DeleteSinglePerson(final Builder<R> builder) {
        super(builder);
    }

    @Override
    protected SingleModelResult<Person> loadModel() {
        return DaoFactory.getInstance().getPersonDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getPersonDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    @Override
    protected void authorizeRequest() {

    }

    public static class Builder<R> extends AbstractDeleteStateBuilder<R> {
        @Override
        public AbstractState<R, Void> build() {
            return new DeleteSinglePerson<>(this);
        }
    }
}
