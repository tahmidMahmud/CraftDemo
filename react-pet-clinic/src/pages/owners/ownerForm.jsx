import React from "react";
import Styles from "../../Stlyes";
import { Form, Field } from "react-final-form";
import ModalForm from "../../components/form/modalForm";

const onSubmit = async (values, onAdd, onUpdate, setOpen, updateValues) => {
  updateValues ? onUpdate(values) : onAdd(values);
  setOpen(false);
};

const OwnerForm = ({ onAdd, onUpdate, open, setOpen, updateValues }) => (
  <ModalForm open={open} setOpen={setOpen}>
    <Styles>
      <h1>{updateValues ? "Modify Owner" : "Add Owner"}</h1>
      <Form
        onSubmit={(values) =>
          onSubmit(values, onAdd, onUpdate, setOpen, updateValues)
        }
        initialValues={updateValues ? updateValues : { pets: [] }}
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
              <label>Address</label>
              <Field
                name="address"
                component="input"
                type="text"
                placeholder="Address"
              />
            </div>
            <div>
              <label>City</label>
              <Field
                name="city"
                component="input"
                type="text"
                placeholder="City"
              />
            </div>
            <div>
              <label>Telephone</label>
              <Field
                name="telephone"
                component="input"
                type="text"
                placeholder="Telephone"
              />
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

export default OwnerForm;
