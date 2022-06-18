import React from "react";
import {
  Button,
  Card,
  CardBody,
  CardTitle,
  Form,
  FormGroup,
  Input,
  Label,
} from "reactstrap";
import styles from "./Registration.module.css";

const Registration = () => {
  return (
    <div className={styles.wrapper}>
      <Card>
        <CardBody>
          <div className="d-flex justify-content-center">
            <CardTitle tag="h5" >Регистрация</CardTitle>
          </div>
          <Form>
          <FormGroup className="mb-2 me-sm-2 mb-sm-0">
              <Label className="me-sm-2" for="lastname">
                Фамилия
              </Label>
              <Input
                id="lastname"
                name="lastname"
                placeholder="Иванов"
                type="text"
              />
            </FormGroup>
            <FormGroup className="mb-2 mt-2 me-sm-2 mb-sm-0">
              <Label className="me-sm-2" for="firstname">
                Имя
              </Label>
              <Input
                id="firstname"
                name="firstname"
                placeholder="Иван"
                type="text"
              />
            </FormGroup>
            <FormGroup className="mb-2 mt-2 me-sm-2 mb-sm-0">
              <Label className="me-sm-2" for="email">
                Email
              </Label>
              <Input
                id="email"
                name="email"
                placeholder="email@dvfu.ru"
                type="email"
              />
            </FormGroup>
            <FormGroup check className="mb-2 mt-4">
              <Input type="checkbox" /> <Label check>Запомнить меня</Label>
            </FormGroup>
            <div className=" d-flex justify-content-center flex-column align-items-center">
            <Button className="mt-3">Зарегистрироваться</Button>
            <a href='/login' className="mt-1"> войти</a>
            </div>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};

export default Registration;
