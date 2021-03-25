import { render, screen } from "@testing-library/react";
import App from "./App";

test("Expect links to all pages to be present", () => {
  render(<App />);
  const tabs = screen.getAllByRole("tab", { href: "/owners" });

  expect(tabs[0]).toHaveAttribute("href", "/owners");
  expect(tabs[1]).toHaveAttribute("href", "/vets");
  expect(tabs[2]).toHaveAttribute("href", "/visits");
});
