import React from "react";
import Styles from "../../Stlyes";
import { Form, Field } from "react-final-form";
import ModalForm from "../../components/form/modalForm";

const onSubmit = async (values, onAdd, onUpdate, setOpen, updateValues) => {
  updateValues ? onUpdate(values) : onAdd(values);
  setOpen(false);
};

const VetForm = ({ onAdd, onUpdate, open, setOpen, updateValues }) => (
  <ModalForm open={open} setOpen={setOpen}>
    <Styles>
      <h1>{updateValues ? "Modify Vet" : "Add Vet"}</h1>
      <Form
        onSubmit={(values) =>
          onSubmit(values, onAdd, onUpdate, setOpen, updateValues)
        }
        initialValues={updateValues ? updateValues : {}}
        render={({ handleSubmit, form, submitting, pristine }) => (
          <form onSubmit={handleSubmit}>
            <div>
              <label>First Name</label>
              <Field
                name="firstName"
                component="input"
                type="text"
                placeholder="First Name"
              />
            </div>
            <div>
              <label>Last Name</label>
              <Field
                name="lastName"
                component="input"
                type="text"
                placeholder="Last Name"
              />
            </div>
            <div>
              <label>Specialties</label>
              <div>
                <label>
                  <Field
                    name="specialties"
                    component="input"
                    type="checkbox"
                    value="radiology"
                  />{" "}
                  Radiology
                </label>
                <label>
                  <Field
                    name="specialties"
                    component="input"
                    type="checkbox"
                    value="surgery"
                  />{" "}
                  Surgery
                </label>
                <label>
                  <Field
                    name="specialties"
                    component="input"
                    type="checkbox"
                    value="dentistry"
                  />{" "}
                  Dentistry
                </label>
              </div>
            </div>
            <div></div>
            <div className="buttons">
              <button type="submit" disabled={submitting || pristine}>
                {updateValues ? "Modify" : "Submit"}
              </button>
              <button
                type="button"
                onClick={form.reset}
                disabled={submitting || pristine}
              >
                Reset
              </button>
            </div>
          </form>
        )}
      />
    </Styles>
  </ModalForm>
);

export default VetForm;
