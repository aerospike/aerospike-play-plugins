/*
 * Copyright (C) 2008-2015 Aerospike, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aerospike.session.impl;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Test class Course
 *
 * @author akshay
 *
 */
@ToString
@EqualsAndHashCode
public class Course implements Serializable {
    private static final long serialVersionUID = 4L;
    public int credits;
    public String instructor;

    public Course(int credits, String Instructor) {
        this.credits = credits;
        this.instructor = Instructor;
    }
}
