/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.appengine.task

import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.InputDirectory

/**
 * Abstract Google App Engine task that requires setting the web application dir.
 *
 * @author Benjamin Muschko
 */
abstract class WebAppDirTask extends AbstractTask {
    @InputDirectory File webAppSourceDirectory

    @Override
    void validateConfiguration(){
        super.validateConfiguration()

        try {
            if(!getWebAppSourceDirectory() || !getWebAppSourceDirectory().exists()) {
                throw new InvalidUserDataException('Webapp source directory '
                        + (!getWebAppSourceDirectory() ? 'null' : getWebAppSourceDirectory().canonicalPath)
                        + ' does not exist')
            }
            else {
                logger.info "Webapp source directory = ${getWebAppSourceDirectory().canonicalPath}"
            }
        }
        catch(IOException e) {
            throw new InvalidUserDataException('Webapp source directory does not exist', e)
        }
    }
}