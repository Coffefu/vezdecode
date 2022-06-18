import aiohttp
from aiohttp import web

HOST_IP = "185.50.25.11"
HOST_POST = 8080


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
    app.router.add_post("/skill_cofefu", skill_cofefu)
    web.run_app(app, host=HOST_IP, port=HOST_POST)


if __name__ == "__main__":
    init()
