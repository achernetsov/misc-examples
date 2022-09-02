#!/usr/bin/env python3
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#

from proton import Message
from proton.handlers import MessagingHandler
from proton.reactor import Container
import json, os
from dotenv import load_dotenv


class Processor(MessagingHandler):
    sender = None

    def __init__(self, server):
        super(Processor, self).__init__()
        self.server = server

    def on_start(self, event):
        conn = event.container.connect(self.server)
        event.container.create_receiver(conn, "quote-requests")
        event.container.create_sender(conn, "quotes")

    def on_sendable(self, event):
        print("sendable")
        self.sender = event.sender
        # event.sender.close()
        # self.sender.close()

    def on_message(self, event):
        print(event.message.body)
        if self.sender is not None:
            print("sending quote")
            quote = {'id': event.message.body, 'price': 10}
            self.sender.send(Message(body=json.dumps(quote)))

load_dotenv()
broker=os.environ['AMQP_HOST']
Container(Processor(f'{broker}:5672')).run()
