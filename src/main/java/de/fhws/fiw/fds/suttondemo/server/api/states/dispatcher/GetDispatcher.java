// Copyright 2022 Peter Braun
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.suttondemo.server.api.states.dispatcher;

import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;
import de.fhws.fiw.fds.suttondemo.server.api.states.persons.PersonRelTypes;
import de.fhws.fiw.fds.suttondemo.server.api.states.persons.PersonUri;

public class GetDispatcher<R> extends AbstractGetDispatcherState<R> {

    public GetDispatcher(Builder<R> builder) {
        super(builder);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PersonUri.REL_PATH, PersonRelTypes.GET_ALL_PERSONS, getAcceptRequestHeader());
    }

    public static class Builder<R> extends AbstractDispatcherStateBuilder<R> {
        @Override
        public GetDispatcher<R> build() {
            return new GetDispatcher<>(this);
        }
    }
}
