/*
 * Copyright (C) 2017 | TS Applications Pty Ltd
 * All Rights Reserved.
 */

package com.sample.thingyselector.ui.handlers


interface OnModelClickListener<in T : Any> {
    fun onClick(model: T)
}