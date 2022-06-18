from aiohttp import web
import json

HOST_IP = "127.0.0.1"
HOST_POST = 3000

def webhook(event, context):
    request_message = json.loads(event['body'])
    print(request_message)
    derived_session_fields = ['session_id', 'user_id', 'message_id']
    response_message = {
        "response": {
            "text": request_message['request']['original_utterance'],
            "tts": request_message['request']['original_utterance'],
            "end_session": False
        },
        "session": {derived_key: request_message['session'][derived_key] for derived_key in
                    derived_session_fields},
        "version": request_message['version']
    }

    return {
        "statusCode": 200,
        "body": json.dumps(response_message)
    }


async def skill_cofefu(request_obj):
    request = await request_obj.json()
    response = {}
    response["version"] = request["version"]
    response["session"] = request["session"]
    response["response"] = {"end_session": False}

    response["response"]["text"] = "Привет!"
    response["response"]["end_session"] = True

    return web.json_response(response)


def init():
    app = web.Application()
    app.router.add_post("/webhook", webhook)
    web.run_app(app, host=HOST_IP, port=HOST_POST)


if __name__ == "__main__":
    init()