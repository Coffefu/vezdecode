const { pick } = require("ramda");

let question = 1;
let answers = [];

module.exports = {
  get_response: function (req) {
    if (req.session.new) {
      return this.clientStart(req);
    }
    if (req.request.command == "нет" || req.request.command == "on_interrupt")
          return this.clientStop(req);
    if (req.request.command == "да!")
          return this.startTest(req);
    if (req.request.command === "cofefu.ru вездеход") {
      return this.team_response(req);
    }

  },
  team_response: function ({ request, session, version }) {
    return {
      response: {
        text:
          ["Привет вездекодерам!"],
        tts:
          "Привет ^вездекодерам!^",
        end_session: false,
      },
      session: pick(["session_id", "message_id", "user_id"], session),
      version
    };
  },
  clientStart: function ({ request, session, version }) {
    return {
      response: {
        text:
          ["Привет! Мы подготовили для вас тест по выбору категории Вездекода. Начнём?"],
        tts:
          "Привет! Мы подготовили для вас тест по выбору категории Вездекода. ^Начнём^?",
        buttons: [
          {
            title: "Да!",
            payload: {},
            url: "",
          },
          {
            title: "Нет",
            payload: {},
            url: ""
          }
        ],
        end_session: false
      },
      session: pick(["session_id", "message_id", "user_id"], session),
      version
    };
  },
  clientStop: function ({ request, session, version }) {
    return {
      response: {
        text:
          "Было приятно с вами поиграть! Возвращайтесь, когда будет удобно!",
        tts:
          "Было приятно с вами поиграть! Возвращайтесь, когда будет удобно!",
        end_session: true
      },
      session: pick(["session_id", "message_id", "user_id"], session),
      version
    };
  },
  startTest: function ({ request, session, version }) {
    return {
      response: {
        text:
          ["1. "],
        tts:
          "Привет! Мы подготовили для вас тест по выбору категории Вездекода. ^Начнём^?",
        buttons: [
          {
            title: "Да!",
            payload: {},
            url: "",
          },
          {
            title: "Нет",
            payload: {},
            url: ""
          }
        ],
        end_session: false
      },
      session: pick(["session_id", "message_id", "user_id"], session),
      version
    };
  },
};
