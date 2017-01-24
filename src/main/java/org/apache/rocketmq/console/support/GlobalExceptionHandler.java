/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.console.support;

import javax.servlet.http.HttpServletRequest;
import org.apache.rocketmq.console.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by songyongzhong on 2017/1/22.
 */

@ControllerAdvice(basePackages = "org.apache.rocketmq.console")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult<Object> jsonErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        JsonResult<Object> value = null;
        if (ex != null) {
            if (ex instanceof ServiceException) {
                value = new JsonResult<Object>(((ServiceException) ex).getCode(), ex.getMessage());
            }
            else {
                value = new JsonResult<Object>(-1, ex.getMessage());
            }
        }
        return value;
    }
}
